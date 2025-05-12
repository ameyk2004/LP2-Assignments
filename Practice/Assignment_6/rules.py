RULES = [
    {
        "condition" : {"fever"},
        "fact" : "infection",
        "explaination" : "0"
    },
    {
        "condition" : {"fatigue", "headache"},
        "fact" : "dehydration",
        "explaination" : "1"
    },
    {
        "condition" : {"runny_nose", "sneezing"},
        "fact" : "cold",
        "explaination" : "2"
    },
    {
        "condition" : {"runny_nose", "cough"},
        "fact" : "cold",
        "explaination" : "3"
    },
    {
        "condition" : {"fever", "fatigue"},
        "fact" : "heat_exhaustion",
        "explaination" : "4"
    },
    {
        "condition" : {"fever", "cold"},
        "fact" : "flu",
        "explaination" : "5"
    },
    {
        "condition" : {"body_ache", "fever", "cough"},
        "fact" : "flu",
        "explaination" : "6"
    },
    {
        "condition" : {"flu", "fatigue"},
        "fact" : "severe_flu",
        "explaination" : "7"
    },
]