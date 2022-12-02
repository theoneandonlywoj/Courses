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

### 2. Practice!
- Open a file with:
```sh
vim <filename>
```
- Close a file with `:q` (Command-line mode). If you have made any changes and you want to discard them use `:q!` (Command-line mode).
- Press `i` to enter Insert mode. Write something.
- Press `ESC` to get back to Normal mode.
- Press `v` to enter Visual mode. Highlight some text.
- Press `y` to yank (copy) the text. You will be in Normal mode after this operation.
- If you want to jump to the end of the line use `$`, and back to the beginning with `0` (zero).
- Paste the text with `p`.
- You can undo that with `u`.
- If you want to save the file use `:w`. You can chain the commands and write and exit with `:wq`. Sometimes you need root access to write to a file. In that case use `:w !sudo tee % `.