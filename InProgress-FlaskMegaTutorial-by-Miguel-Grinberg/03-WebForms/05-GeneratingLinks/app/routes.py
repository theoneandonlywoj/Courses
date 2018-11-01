from flask import render_template, flash, redirect, url_for
from main import app
from app.forms import LoginForm

@app.route('/')
@app.route('/index')
def indexFunction():
    user = {'username': 'Wojciech'}
    posts = [
        {
            'author': {'username': 'John'},
            'body': 'Beautiful day in Portland!'
        },
        {
            'author': {'username': 'Susan'},
            'body': 'The Avengers movie was so cool!'
        }
    ]
    return render_template('index.html', title='Home', user=user, posts=posts)

@app.route('/login', methods=['GET', 'POST'])
def loginFunction():
    form = LoginForm()
    if form.validate_on_submit():
        flash('Login requested for user {}, remember_me={}'.format(
            form.username.data, form.remember_me.data))
        return redirect(url_for('indexFunction'))
    return render_template('login.html', title='Sign In', form=form)
