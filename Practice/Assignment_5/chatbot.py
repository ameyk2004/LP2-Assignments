from nltk.corpus import wordnet, stopwords
from nltk.stem import WordNetLemmatizer
from nltk.tag import pos_tag
from nltk.tokenize import word_tokenize
import random
from rules import rules

def get_pos(pos: str):
    
    if pos.startswith('N'):
        return wordnet.NOUN
    
    if pos.startswith('J'):
        return wordnet.ADJ
    
    if pos.startswith('V'):
        return wordnet.VERB
    
    if pos.startswith('R'):
        return wordnet.ADV
    
    return wordnet.NOUN

def process_query(query: str):
    query = query.lower()
    query_tokens = word_tokenize(query)
    stop_words = set(stopwords.words('english'))
    
    query_tokens = [word for word in query_tokens if word not in stop_words]
    
    pos_tags = pos_tag(query_tokens)
    
    lemmas = []
    lemmatizer = WordNetLemmatizer()
    for word, pos in pos_tags:
        lemmas.append(lemmatizer.lemmatize(word=word, pos=get_pos(pos)))
        
    for keywords, responses in rules.items():
        for keyword in keywords:
            if keyword in word_tokenize(query):
                return random.choice(responses)
            
    for keywords, responses in rules.items():
        for keyword in keywords:
            if keyword in lemmas:
                return random.choice(responses)
            
    return random.choice([
        "Sorry No answer",
        "Cant help !",
        "Please rephrase"
    ])
        
        

def main():
    print("Welcome to Brew Buddy !!")
    while(True):
        user_query = str(input("\n>> "))
        response = process_query(user_query)
        print(f"Chatbot >> {response}")

if __name__ == '__main__':
    main()