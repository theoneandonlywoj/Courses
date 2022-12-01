# Vim Essentials - One Hour to Proficiency
https://www.udemy.com/course/vim-essentials

## Notes
### 1.Vim modes
- You can bring vim modes helper by typing:
```vim 
:h vim-modes
```

- Vim has 7 modes. The most important 4 are:
    - Normal
        - Default starting mode.
        - You can access this mode from other modes by pressing `ESC`.
        - Normally used to read the document.
        - It doesn't have an indicator (bottom left corner).
    - Insert
        - Press `i` to enter the mode from Normal mode.
        - Used to edit text normally.
        - It will be indicated by `-- INSERT --` (bottom left corner).
        - Press `ESC` to enter Normal Mode.
    - Visual
        - Press `v` to enter the mode from Normal mode.
        - Useful to apply commands on selections of text.
        - Similar to clicking and dragging to highlight text.
        - It will be indicated by `-- VISUAL --` (bottom left corner).
        - Press `ESC` to enter Normal Mode.
    - Command-line
        - Enter the mode with `:` from Normal mode.
        - `:` follow by text in order to modify settings, save or quit the file.
        - It can be used to perform searches and replace or browse the help menu.
        - Press `ESC` to enter Normal Mode.
        - It will be indicated by `:` (bottom left corner).