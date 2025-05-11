# Imports

import nltk

from rules import rules
from nltk.corpus import wordnet, stopwords
from nltk.tag import pos_tag
from nltk.tokenize import word_tokenize
from nltk.stem import WordNetLemmatizer
import random

def get_pos(pos:str):
    if pos.startswith('N'):
        return wordnet.NOUN
    if pos.startswith('J'):
        return wordnet.ADJ
    if pos.startswith('V'):
        return wordnet.VERB
    if pos.startswith('R'):
        return wordnet.ADJ
    
    return wordnet.NOUN

def process_query(query):
    
    query = query.lower()
    
    ##Tokenization
    query_tokens = word_tokenize(query)
    
    ##stop word removal
    stop_words = set(stopwords.words('english'))
    query_tokens = [word for word in query_tokens if word not in stop_words]
    
    
    pos_tokens = pos_tag(query_tokens)
    
    ##Lemmatization
    lemmas = []
    lemmatizer = WordNetLemmatizer()
    
    for word, pos in pos_tokens:
        lemmas.append(lemmatizer.lemmatize(word=word, pos=get_pos(pos)))
    
    
    for keywords, responses in rules.items():
        for keyword in word_tokenize(query):
            if keyword in keywords:
                return random.choice(responses)
            
    for keywords, responses in rules.items():
        for keyword in lemmas:
            if keyword in keywords:
                return random.choice(responses)
            
    return random.choice([
        "I'm sorry, I didn't quite get that.",
        "Hmm, I'm not sure about that.",
        "Could you rephrase?"
    ])

def main():
    print("Welcome to Brew Buddy !!")
    while(True):
        user_input = str(input("\n>> "))
        response = process_query(user_input)
        print(f"Brewyard >> {response}")
        
if __name__ == "__main__":
    main()