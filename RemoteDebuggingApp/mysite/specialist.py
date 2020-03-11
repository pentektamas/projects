import rpyc

def main():
   print('Enter client ip: ')
   ip = input()
   conn = rpyc.connect(ip, 18812)
   while True:
       print('>>', end=' ')
       command = input()
       print(conn.root.run_command(command))

if __name__ == '__main__':
   main()
