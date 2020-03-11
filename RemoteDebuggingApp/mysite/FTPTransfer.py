import ftplib


class FTPTransfer:

    def ftp_connect(self, site_address, port):
        try:
            ftp = ftplib.FTP('')
            # face conexiunea cu serverul la adresa site_address si portul port
            ftp.connect(site_address, port)
            # face login cu userul userName si parola ABCdef123!
            ftp.login("userName", "ABCdef123!")
            print(ftp.getwelcome())
            print('Current Directory', ftp.pwd())
            #afiseaza continutul directorului curent
            ftp.dir()
            self.ftp_aux(ftp)
            #inchide conexiunea cu FTP Server
            ftp.quit()
        except ftplib.all_errors as e:
            print('Failed to connect, check your address and credentials.', e)

    def ftp_download(self, ftp, file_address):
        try:
            #descarca fisierul file_address de pe server in folderul curent
            ftp.retrbinary('RETR ' + file_address, open(file_address, 'wb').write)
            print('File successfully downloaded.')
        except ftplib.error_perm as e:  # Handle 550 (not found / no permission error)
            error_code = str(e).split(None, 1)
            if error_code[0] == '550':
                print(error_code[1], 'File may not exist or you may not have permission to view it.')

    def ftp_upload(self, ftp, file_address):
        try:
            # incarca fisierul file_address din folderul curent pe server
            ftp.storbinary('STOR ' + file_address, open(file_address, 'rb'))
            print('File successfully uploaded.')
        except ftplib.error_perm as e:  # Handle 550 (not found / no permission error)
            error_code = str(e).split(None, 1)
            if error_code[0] == '550':
                print(error_code[1], 'File may not exist or you may not have permission to view it.')

    def ftp_aux(self, ftp):
        file = "Bot.exe"
        self.ftp_download(ftp, file)


if __name__ == "__main__":
    address = input('Enter the IP address of the FTP server: ')
    # face conexiunea cu server (adresa address, portul 1027) si descarca fisierul Bot.exe
    FTPTransfer().ftp_connect(address, 1027)
