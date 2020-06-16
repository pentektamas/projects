# Copyright (c) Microsoft Corporation. All rights reserved.
# Licensed under the MIT License.
from tempfile import TemporaryFile

from botbuilder.core import ActivityHandler, TurnContext
from botbuilder.schema import ChannelAccount
from gtts import gTTS
import speech_recognition as sr
from FSM import FSM
from predict import Predict
import azure.cognitiveservices.speech as speechsdk
from pygame import mixer


class MyBot(ActivityHandler):
    def __init__(self):
        self.fsm = FSM()
        self.isSet = False

    def talk_back(self, response):
        mixer.init()
        r = sr.Recognizer()
        with sr.Microphone() as source:
            r.adjust_for_ambient_noise(source)
            tts = gTTS(text=str(response), lang='en')
            sf = TemporaryFile()
            tts.write_to_fp(sf)
            sf.seek(0)
            mixer.music.load(sf)
            mixer.music.play()
            while mixer.music.get_busy():
                pass

    def recognise_luis(self):
        intent_config = speechsdk.SpeechConfig(
            subscription="19e4e3119feb4a6da322fa291cf84ce5",
            region="westus")
        intent_recognizer = speechsdk.intent.IntentRecognizer(speech_config=intent_config)
        stopped = False

        while not stopped:
            intent_result = intent_recognizer.recognize_once()
            if intent_result is not None:
                if intent_result.text == "Voice off.":
                    stopped = True
                elif intent_result.text != "":
                    result = Predict.get_result(intent_result.text)
                    var = result['prediction']['intents']
                    if result['prediction']['topIntent'] == 'Remark' and len(
                            list(result['prediction']['entities'])) == 0 or list(var.values())[0]['score'] <= 0.5:
                        to_say = "I did not understand that."
                    else:
                        self.fsm.setMessage(result)
                        to_say = self.fsm.getResponse()
                    self.talk_back(to_say)

    async def on_message_activity(self, turn_context: TurnContext):
        if turn_context.activity.text == "voice on" and self.isSet == True:
            message = "Speech recognition mode on"
            await turn_context.send_activity(f"{message}")
            self.recognise_luis()
        else:
            if turn_context.activity.text == "start bot":
                self.fsm.start()
                self.isSet = True
                message = "The bot is listening to you."
                await turn_context.send_activity(f"{message}")
            elif self.isSet == True and turn_context.activity.text == "stop bot":
                self.fsm.stop()
                message = "Shutting down."
                await turn_context.send_activity(f"{message}")
            elif self.isSet == True:
                result = Predict.get_result(turn_context.activity.text)
                self.fsm.setMessage(result)
                await turn_context.send_activity(f"{self.fsm.getResponse()}")

    async def on_members_added_activity(
            self,
            members_added: ChannelAccount,
            turn_context: TurnContext
    ):
        for member_added in members_added:
            if member_added.id != turn_context.activity.recipient.id:
                await turn_context.send_activity(
                    "Enter \"start bot\" to start the conversation with the assistant.\n To close it enter \"stop bot\"")
