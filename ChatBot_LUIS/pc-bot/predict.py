########### Python 3.6 #############
import requests


class Predict:
    @staticmethod
    def get_result(utterance):
        try:
            key = 'dbed6a24f4994e1d8ebd27c15e8c5c8c' # your Runtime key
            endpoint = 'westus.api.cognitive.microsoft.com' # such as 'your-resource-name.api.cognitive.microsoft.com'
            appId = 'da105e46-9bce-43fa-911a-ec08c3739ddc'
            #  utterance = 'what is scheduled tomorrow'

            headers = {
            }

            params ={
                'query': utterance,
                'timezoneOffset': '0',
                'verbose': 'true',
                'show-all-intents': 'true',
                'spellCheck': 'false',
                'staging': 'false',
                'subscription-key': key
            }

            r = requests.get(f'https://{endpoint}/luis/prediction/v3.0/apps/{appId}/slots/production/predict',headers=headers, params=params)
            print(r.json())
            return r.json()
        except Exception as e:
            print(f'{e}')
