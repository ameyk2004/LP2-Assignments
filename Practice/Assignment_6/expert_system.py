
from rules import RULES

UNIQUE_CONDITIONS = set()
FACTS = set()

def ask_user_input():
    
    for rule in RULES:
        for condition in rule.get("condition"):
            UNIQUE_CONDITIONS.add(condition)
    
    print(UNIQUE_CONDITIONS)
    
    for condition in UNIQUE_CONDITIONS:
        user_input = str(input(f"Do you have {condition} ? (y/n) >> ")).lower()
        if(user_input.startswith('y')):
            FACTS.add(condition)
            
    print(FACTS)

def forward_chain():
    conclusions = set()
    applied_rules = set()
    flag = True
    
    while flag:
        flag = False
        
        for i, rule in enumerate(RULES):
            
            if i in applied_rules:
                continue
            
            if(rule.get("condition").issubset(FACTS)):
                new_fact = rule.get("fact")
                if new_fact not in FACTS:
                    FACTS.add(new_fact)
                    conclusions.add(rule.get("explaination"))
                    applied_rules.add(i)
                    flag = True
                    
    
    for conclusion in conclusions:
        print(conclusion)
                    

def main():
    ask_user_input()
    
    if(len(FACTS) == 0):
        print("NOT DIAGNOSABLE")
        
    forward_chain()


if __name__ == '__main__':
    main()