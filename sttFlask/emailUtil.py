import os
import smtplib

from email import encoders
from email.utils import formataddr
from email.mime.base import MIMEBase
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

def test():
    username = os.environ['MAIL_USERNAME']
    print(username)

def make_file(data):
    print('make file')
    # os.path.join(os.getcwd(), 'storage', 'rude.jpg')



def stt_send_mail(date, recipients, counselor_name, file_url):

    sender = os.environ['MAIL_USERNAME']
    password = os.environ['MAIL_PW']

    # password = os.environ['MAIL_PW']
    print(sender)

    from_addr = formataddr(('Sodam Service', sender))
    to_addr = formataddr((str(counselor_name + ' 상담사님'), recipients))

    session = None
    try:
        # SMTP 세션 생성
        session = smtplib.SMTP('smtp.gmail.com', 587)
        session.set_debuglevel(True)

        # SMTP 계정 인증 설정
        session.ehlo()
        session.starttls()
        session.login(sender, password)

        # 메일 콘텐츠 설정
        message = MIMEMultipart("mixed")

        # 메일 송/수신 옵션 설정
        message.set_charset('utf-8')
        message['From'] = from_addr
        message['To'] = to_addr
        message['Subject'] = '[소담]' + ' ' + date + ' 세션 기록 송부'

        # 메일 콘텐츠 - 내용
        body = '''
<div style='margin:100px;'>
    <div align='center' style='background-color:#eeeeee; font-family:verdana; padding:0.5em; '>
        <h1 style='padding-bottom:10px; '>세션 기록을 확인하세요 🎉 </h1>
        <hr/>
        <div style='font-size:130%'>
            안녕하세요. 소담입니다.<br>
            <strong>''' + date + ''' </strong> 세션 기록이 왔어요!
            <br>
            <div>앞으로도 저희 서비스 이용 부탁드려요 :D </div>
            <div>
                <br/>
            </div>
        </div></div></div>
        '''
        bodyPart = MIMEText(body, 'html', 'utf-8')
        message.attach(bodyPart)

        # 메일 콘텐츠 - 첨부파일
        attachments = [
            os.path.join(os.getcwd(), 'storage', '0d6c94b1-7e29-4b26-969e-5f2b76daa39f_SessionA~4.txt')
            # file_url
        ]

        for attachment in attachments:
            attach_binary = MIMEBase("application", "octect-stream")
            try:
                binary = open(attachment, "rb").read()  # read file to bytes

                attach_binary.set_payload(binary)
                encoders.encode_base64(attach_binary)  # Content-Transfer-Encoding: base64

                filename = os.path.basename(attachment)
                attach_binary.add_header("Content-Disposition", 'attachment', filename=('utf-8', '', filename))

                message.attach(attach_binary)
            except Exception as e:
                print(e)

        # 메일 발송
        session.sendmail(from_addr, to_addr, message.as_string())

        print('Successfully sent the mail!!!')
    except Exception as e:
        print(e)
    finally:
        if session is not None:
            session.quit()

if __name__=="__main__":
    stt_send_mail("2023.02.14","entp1618@gmail.com",  "김아현",  "dd")
