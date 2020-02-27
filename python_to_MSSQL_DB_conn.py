# > pip install pyodbc

import pyodbc 
conn = pyodbc.connect('Driver={SQL Server};'
                      'Server=server_name;'
                      'Database=db_name;'
                      'Trusted_Connection=yes;')

cursor = conn.cursor()
cursor.execute('SELECT * FROM db_name.Table')

for row in cursor:
    print(row)

    
#################
import pyodbc
 
conn = pyodbc.connect(r'Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:\Users\Ron\Desktop\Test\testdb.accdb;')
cursor = conn.cursor()

cursor.execute(
'''select
sum(((units_ordered) * (product_price_per_unit))) AS total_revenue
from tracking_sales''')
    
for row in cursor.fetchall():
    print (row)
