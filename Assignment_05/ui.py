import streamlit as st
from chatbot import process_query

st.set_page_config(page_title="Brew Buddy")
st.title("Brew Buddy")

st.session_state.messages = st.session_state.get("messages", [])

for m in st.session_state.messages:
    st.markdown(f"**{m['sender']}**: {m['text']}")

with st.form("chat", clear_on_submit=True):
    q = st.text_input("You:")
    if st.form_submit_button("Send") and q:
        st.session_state.messages += [
            {"sender": "You", "text": q},
            {"sender": "Brewyard", "text": process_query(q)}
        ]
        st.experimental_rerun()
