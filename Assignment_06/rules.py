RULES = [
    {
        "condition" : {"fever"},
        "fact" : "infection",
        "explanation" : "Fever can be early signs of Infection"
    },
    
    {
        "condition" : {"fatigue", "headache"},
        "fact" : "dehydration",
        "explanation" : "Headache and fatigue can be caused by dehydration"
    },
    
    {
        "condition" : {"runny_nose", "sneezing"},
        "fact" : "cold",
        "explanation" : "Runny nose and sneezing are common due to cold"
    },
    
    {
        "condition" : {"cough", "sneezing"},
        "fact" : "cold",
        "explanation" : "cold can be due to cough and light sneezing"
    },
    
    {
        "condition" : {"fever", "fatigue"},
        "fact" : "heat_wave_exhaustion",
        "explanation" : "fever and fatigue can be due to heat wave exhaustion"
    },
    
    {
        "condition" : {"fever", "cold"},
        "fact" : "flu",
        "explanation" : "flu can be due to fever with cold"
    },
    
    {
        "condition" : {"body_aches", "fever", "cough"},
        "fact" : "flu",
        "explanation" : "These conditions can cause flue intensification"
    },
    
    {
        "condition" : {"flu", "fatigue"},
        "fact" : "severe_flu",
        "explanation" : "These conditions can cause flue severely"
    },
    
    
]