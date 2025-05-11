
from chatbot import process_query
import gradio as gr

# Launch Gradio Interface
iface = gr.Interface(fn=process_query, 
                     inputs="text", 
                     outputs="text", 
                     title="Cafe Chatbot", 
                     description="Ask me anything about our cafe! (e.g., menu, hours, coffee, etc.)")
iface.launch()