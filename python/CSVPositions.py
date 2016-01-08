# CSV Positions Handler for Giz

import csv
import MySQLdb

#Read CSV file
with open('../csv/Positions.csv') as csvfile:
    readCSV = csv.reader(csvfile, delimiter=';')
    datetimes = []
    unitids = []
    rdxs = []
    rdys = []
    speeds = []
    courses = []
    numsals = []
    hdops = []
    quals = []

    #Loop and append to array
    for row in readCSV:
        datetimes.append(row[0])
        unitids.append(row[1])
        rdxs.append(row[2])
        rdys.append(row[3])
        speeds.append(row[4])
        courses.append(row[5])
        numsals.append(row[6])
        hdops.append(row[7])
        quals.append(row[8])

    datestimecol = datetimes[0]
    unitidcol = unitids[0]
    rdxscol = rdxs[0]
    rdyscol = rdys[0]
    speedcol = speeds[0]
    coursecol = courses[0]
    numsalcol = numsals[0]
    hdopcol = hdops[0]
    qualcol = quals[0]

    datetimes.pop(0)
    unitids.pop(0)
    rdxs.pop(0)
    rdys.pop(0)
    speeds.pop(0)
    courses.pop(0)
    numsals.pop(0)
    hdops.pop(0)
    quals.pop(0)

    db = MySQLdb.connect("localhost","root","root","CSV")
    cursor = db.cursor()

    createtable = "CREATE TABLE POSITIONS " + "("+ datestimecol + " DATETIME NOT NULL, " + unitidcol + " BIGINT(100), " + rdxscol + " DOUBLE NOT NULL, " + rdyscol + " DOUBLE NOT NULL, " + speedcol + " INT(10), " + coursecol + " INT(10), " + numsalcol + " INT(10), " + hdopcol + " INT(10), " + qualcol + " VARCHAR(20)) "
    cursor.execute(createtable)
    #print(createtable)

    for i in xrange(1,len(unitids)):
        insertall = "INSERT INTO POSITIONS " + "("+datestimecol+", "+unitidcol+", "+rdxscol+", "+rdyscol+", "+speedcol+", "+coursecol+", "+numsalcol+", "+hdopcol+", "+qualcol+") VALUES (""'"+datetimes[i]+"',"+"'"+unitids[i]+"',"+"'"+rdxs[i]+"',""'"+rdys[i]+"',""'"+speeds[i]+"',""'"+courses[i]+"',""'"+numsals[i]+"',""'"+hdops[i]+"',""'"+quals[i]+"')"
        #print(insertall)
        cursor.execute(insertall)
        db.commit()



