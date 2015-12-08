# CSV Monitoring Handler for Giz

import csv
import MySQLdb

#Read CSV file
with open('../csv/Monitoring.csv') as csvfile:
    readCSV = csv.reader(csvfile, delimiter=';')
    unitids = []
    begintimes = []
    endtimes = []
    types = []
    mins = []
    maxs = []
    sums = []

    #Loop and append to array
    for row in readCSV:
        unitids.append(row[0])
        begintimes.append(row[1])
        endtimes.append(row[2])
        types.append(row[3])
        mins.append(row[4])
        maxs.append(row[5])
        sums.append(row[6])

    unitidcol = unitids[0]
    begintimecol = begintimes[0]
    endtimecol = endtimes[0]
    typecol = types[0]
    mincol = mins[0]
    maxcol = maxs[0]
    sumcol = sums[0]

    unitids.pop(0)
    begintimes.pop(0)
    endtimes.pop(0)
    types.pop(0)
    mins.pop(0)
    maxs.pop(0)
    sums.pop(0)

    db = MySQLdb.connect("localhost","root","root","CSV")
    cursor = db.cursor()

    createtable = "CREATE TABLE MONITORING " + "("+ unitidcol + " INT(20), " + begintimecol + " DATETIME NOT NULL, " + endtimecol + " DATETIME NOT NULL, " + typecol + " VARCHAR(100), " + mincol + " INT(100), " + maxcol + " INT(100), " + sumcol + " BIGINT(100)) "
    cursor.execute(createtable)
    #print(createtable)

    for i in xrange(1,len(unitids)):
        insertall = "INSERT INTO MONITORING " + "("+unitidcol+", "+begintimecol+", "+endtimecol+", "+typecol+", "+mincol+", "+maxcol+", "+sumcol+") VALUES (""'"+unitids[i]+"',"+"'"+begintimes[i]+"',"+"'"+endtimes[i]+"',""'"+types[i]+"',""'"+mins[i]+"',""'"+maxs[i]+"',""'"+sums[i]+"')"
        print(insertall)
        cursor.execute(insertall)
        db.commit()



