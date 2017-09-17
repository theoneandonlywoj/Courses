import tkinter as tk 
from tkinter import ttk # ttk = themed TK

def clickMe(Label): # 5
        action.configure(text="** I have been Clicked! **")
        aLabel.configure(foreground='red')
        aLabel.configure(background='green')



if __name__ == '__main__':
    # Calling a TK constructor
    window = tk.Tk() 
    window.title("CryptoAI")
    
    '''
    Disabling windows resizing
    x, y 
    window.resizable(0, 1) disables resizing on the x-axis
    '''
    
    aLabel = ttk.Label(window, text="A Label") # 2
    aLabel.grid(column=0, row=0) 
    
    # Adding a Button # 6
    action = ttk.Button(window, text="Click Me!", command=clickMe) # 7
    action.grid(column=1, row=0) # 8
    # Endless loop
    window.mainloop() 