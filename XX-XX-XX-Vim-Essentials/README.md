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
- If you want to save the file use `:w`. You can chain the commands and write and exit with `:wq`. Sometimes you need root access to write to a file. In that case use `:w !sudo tee % `
- If you want to save the file with a new name but continue editing this file, use `:w <new_filename>`

### 3. Move around!
- There are two ways you can perform directional movements:
    - Arrows
    - h (left), j (up), k (down), l (right)
- To navigate to the end of the line, use `$`.
- To navigate to the beginning of the line, use `0` (zero).
- To jump forward a word - `w`.
- To jump forward a word - `b`.
- To jump to the top of a file - `G`.
- To jump to the bottom of a file - `gg` or `1G`.
- To scroll a half page down - `Ctrl + d`.
- To scroll a half page up - `Ctrl + u`.
- To navigate to a line - `:<line number>`, f.e. `:15`
- To highlight text across multiple lines down, navigate to your starting line with `:<line number>`, f.e. `:15`, press `v` to enter Visual mode and `<number of lines>j` or `<number of lines>`&#8595;, f.e. `10j` or 10 &#8595;. Now you selected text from the starting cursor on line 15 to the corresponding place on line 25.
- To highlight text across multiple lines down, navigate to your starting line with `:<line number>`, f.e. `:15`, press `v` to enter Visual mode and `<number of lines>k` or `<number of lines>`&#8593;, f.e. `10j` or `10` &#8593;. Now you selected text from the starting cursor on line 15 but starting from line 5 to the corresponding place on line 15.

### 4. Settings
- To enable line numbers - use `:set number`.
- To disable line numbers - use `:set nonumber`.

### 5. Practice !
1. Open any file on your system with Vim, preferably one that has a few hundred lines of text (or several screens full of text). 
2. Move around with the arrow keys and hjkl.
3. Save a copy of the file as  navigatingpractice.txt.
4. Quit without saving.
5. Open navigatingpractice.txt (the copy of the previous file) with Vim.
6. Turn on line numbers.
7. Navigate to line 15 with a single command in command-line mode.
8. Navigate to the end of a longer line with one key.
9. Navigate back to the beginning of the line with one key.
10. Navigate to the end of the line one word as a time.
11. Navigate back to the beginning of the line one word at a time.
12. Navigate to the end of the file with one key.
13. Navigate to the beginning (top) of the file with one key.
14. Navigate a half-page down continuously until the bottom of the file is reached.
15. Navigate a half-page up continuously until the top of the file is reached.
