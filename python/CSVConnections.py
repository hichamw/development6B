# CSV Connections Handler for Giz

import csv
import MySQLdb

#Read CSV file
with open('../csv/Connections.csv') as csvfile:
    readCSV = csv.reader(csvfile, delimiter=';')
    datetime = []
    unitids = []
    ports = []
    values = []

    #Loop and append to array
    for row in readCSV:
        datetime.append(row[0])
        unitids.append(row[1])
        ports.append(row[2])
        values.append(row[3])

    datetimecol = datetime[0]
    unitidcol = unitids[0]
    portcol = ports[0]
    valuecol = values[0]

    datetime.pop(0)
    unitids.pop(0)
    ports.pop(0)
    values.pop(0)

    db = MySQLdb.connect("localhost","root","root","CSV")
    cursor = db.cursor()

    createtable = "CREATE TABLE CONNECTIONS " + "("+ datetimecol + " DATETIME NOT NULL, " + unitidcol + " BIGINT(200), " + portcol + " VARCHAR(20), " + valuecol + " INT(10)) "
    cursor.execute(createtable)

    for i in xrange(1,len(datetime)):
        insertall = "INSERT INTO CONNECTIONS " + "("+datetimecol+", "+unitidcol+", "+portcol+", "+valuecol+") VALUES (""'"+datetime[i]+"',"+"'"+unitids[i]+"',"+"'"+ports[i]+"',""'"+values[i]+"') "
        #print(insertall)
        cursor.execute(insertall)
        db.commit()



