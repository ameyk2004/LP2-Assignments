from rules import RULES

UNIQUE_CONDITIONS = set()
FACTS = set()

def ask_user_facts():
    for rule in RULES:
        for condition in rule.get("condition"):
            UNIQUE_CONDITIONS.add(condition)
    
    print(UNIQUE_CONDITIONS)
    
    for condition in UNIQUE_CONDITIONS:
        user_choice = str(input(f"Do you have {condition} ? (y/n) >> "))
        if(user_choice.lower().startswith('y')):
            FACTS.add(condition)
        
    print(FACTS)
    
def forward_chain():
    conclusions = set()
    applied_rules = set()
    flag = True
    
    while(flag):
        flag = False
        
        for i, rule in enumerate(RULES):
            if i in applied_rules:
                continue
            
            if(rule.get("condition").issubset(FACTS)):
                new_fact = rule.get("fact")
                if(new_fact not in FACTS):
                    FACTS.add(new_fact)
                    conclusions.add(rule.get("explanation"))
                    applied_rules.add(i)
                    flag = True
                    
                    
    print("=== Possible Conclusions ===")
    for conclusion in list(conclusions):
        print(conclusion)
    

def main():
    ask_user_facts()
    if(len(FACTS) == 0):
        print("Not diagnosable")
    
    forward_chain()

if __name__ == '__main__':
    main()