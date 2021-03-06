from flask import Flask, render_template, request, redirect
from flask_mysqldb import MySQL
import yaml

app = Flask(__name__)

#MySQL configurations
db = yaml.load(open('db.yaml'))
app.config['MYSQL_HOST'] = db['mysql_host']
app.config['MYSQL_USER'] = db['mysql_user']
app.config['MYSQL_PASSWORD'] = db['mysql_password']
app.config['MYSQL_DB'] = db['mysql_db']
mysql = MySQL(app)

@app.route('/')
def welcome():
    return 'Notes API'

def authorize(name, password):
    cur = mysql.connection.cursor()
    try:
        result = cur.execute("SELECT password FROM users WHERE user = (%s)",(name))
        if result > 0:
            details = cur.fetchall()
            if details[0] == password:
                return True
            else:
                return False
        else:
            return False
    except MySQLdb.IntegrityError:
        return False
    finally:
        cur.close()

def getNamePass(postDetails):
    return (postDetails['name'], postDetails['pass'])

#new note
@app.route('/new', methods = ['POST'])
def newPost():
    postDetails = request.get_json()
    name, password = getNamePass(postDetails)
    if authorize(name, password) == True:
        cur = mysql.connection.cursor()
        noteName = postDetails['noteName']
        noteDesc = postDetails['noteDesc']
        try:
            affected_count = cur.execute("INSERT INTO notes(user, noteName, noteDesc) VALUES(%s, %s, %s)",(name, noteName, noteDesc))
            mysql.connection.commit()
            return 'success'
        except MySQLdb.IntegrityError:
            return 'failed'
        finally:
            cur.close()

#new Account
@app.route('/newAcc', methods = ['POST'])
def newAcc():
    postDetails = request.get_json()
    name, password = getNamePass(postDetails)
    cur = mysql.connection.cursor()
    try:
        affected_count = cur.execute("INSERT INTO users(user, password) VALUES(%s, %s)",(name, password))
        mysql.connection.commit()
        return 'success'
    except MySQLdb.IntegrityError:
        return 'failed'
    finally:
        cur.close()

#view for all Note Names
@app.route('/viewAll', methods = ['GET'])
def newPost():
    postDetails = request.get_json()
    name, password = getNamePass(postDetails)
    if authorize(name, password) == True:
        cur = mysql.connection.cursor()
        try:
            result = cur.execute("SELECT noteName FROM notes WHERE user = (%s)",(name))
            if result > 0:
                details = cur.fetchall()
                return render_template('index.html', details=details)
            else:
                return 'empty'
        except MySQLdb.IntegrityError:
            return 'failed'
        finally:
            cur.close()


#view for notes by noteName
@app.route('/view', methods = ['GET'])
def newPost():
    postDetails = request.get_json()
    name, password = getNamePass(postDetails)
    if authorize(name, password) == True:
        cur = mysql.connection.cursor()
        noteName = postDetails['noteName']
        try:
            result = cur.execute("SELECT noteDesc FROM notes WHERE user = (%s) and noteName = (%s)",(name),(noteName))
            if result > 0:
                details = cur.fetchall()
                return render_template('index.html', details=details)
            else:
                return 'empty'
        except MySQLdb.IntegrityError:
            return 'failed'
        finally:
            cur.close()


#note update
@app.route('/update', methods = ['PUT'])
def updateDesc():
   #Get item from the POST body
   postDetails = request.get_json()
   name, password = getNamePass(postDetails)
   if authorize(name, password) == True:
       noteName = postDetails['noteName']
       noteDesc = req_data['noteDesc']
       try:
           result = cur.execute("Update notes SET notesDesc = %s WHERE user = (%s) and noteName = (%s)",(noteDesc),(name),(noteName))
           mysql.connection.commit()
           return 'success'
       except MySQLdb.IntegrityError:
           return 'failed'
       finally:
           cur.close()


#delete note
@app.route('/delete', methods = ['DELETE'])
def delete():
   #Get item from the POST body
   postDetails = request.get_json()
   name, password = getNamePass(postDetails)
   if authorize(name, password) == True:
       noteName = postDetails['noteName']
       try:
           result = cur.execute("DELETE from notes where noteName = (%s) and user = (%s)",(noteName),(name))
           mysql.connection.commit()
           return 'success'
       except MySQLdb.IntegrityError:
           return 'failed'
       finally:
           cur.close()


if __name__ == '__main__':
    app.run(debug=True)
