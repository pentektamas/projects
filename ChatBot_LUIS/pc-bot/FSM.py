import random


class FSM:
    def __init__(self):
        self.eqClass = {0: {"Chassis": 0},
                        1: {"Motherboard": 0},
                        2: {"CPU": 0, "GPU": 0},
                        3: {"Cooler": 0},
                        4: {"RAM": 0, "Battery": 0, "Hard disk": 0, "WiFi": 0},
                        5: {"Speakers": 0, "Screen": 0},
                        6: {"Keyboard": 0, "Webcam": 0},
                        7: {"Trackpad": 0}
                        }
        self.gg = {0: "Looks fine.",
                   1: "Nice move.",
                   2: "Great!",
                   3: "Cool! It's a pleasure to work with you.",
                   4: "You have skills",
                   5: "Amazing",
                   6: "You've placed that perfectly",
                   7: "Good job!",
                   8: "Very accurate setup."
                   }
        self.topIntent = None
        self.entity = None
        self.value = None
        self.respone = None
        self.stopped = True
        self.state = -1

    def setMessage(self, result):
        self.topIntent = result['prediction']['topIntent']
        if self.topIntent == "Remark":
            dict = result['prediction']['entities']
            keys = dict.keys()
            self.entity = list(keys)[0]
            lis = dict.get(self.entity)[0]
            self.value = lis[0]

    def query(self):
        message = "At this step you have to place the following:\n"
        entered = False
        if not self.stopped:
            for key, value in self.eqClass.get(self.state).items():
                if value == 0:
                    entered = True
                    message += "- " + key + "\n"
        if not entered:
            return "No components remained. The system is ready!\n"
        else:
            return message

    def list_command(self):
        message = "All the remained components for the system to be ready:\n"
        entered = False
        if not self.stopped:
            for i in range(0, 8):
                for key, value in self.eqClass.get(i).items():
                    if value == 0:
                        entered = True
                        message += "- " + key + "\n"
        if not entered:
            return "No components remained. The system is ready!\n"
        else:
            return message

    def getResponse(self):
        if self.topIntent == 'Remark':
            self.update_state()
        elif self.topIntent == 'Question':
            self.respone = self.query()
        elif self.topIntent == 'ListCommand':
            self.respone = self.list_command()
        return self.respone

    def condition_not_set(self):
        if self.stopped == False and self.entity in self.eqClass.get(self.state):
            if self.eqClass.get(self.state).get(self.entity) == 0:
                return True
        return False

    def can_move(self):
        if not self.stopped:
            for integer in self.eqClass.get(self.state).values():
                if integer == 0:
                    return False
        return True

    def get_random_message(self):
        index = random.randint(0, 8)
        return self.gg.get(index)

    def update_state(self):
        if self.condition_not_set():
            self.eqClass.get(self.state).__setitem__(self.entity, 1)
            self.respone = self.get_random_message()
        else:
            if str(self.value).lower() != str(self.entity).lower():
                self.respone = self.value + " of type " + self.entity + " is not expected at this step."
            else:
                self.respone = self.value + " is not expected at this step."
        if self.can_move():
            if self.state < 7:
                self.state = self.state + 1

    def start(self):
        self.state = self.state + 1
        self.stopped = False

    def stop(self):
        self.stopped = True
