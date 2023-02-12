import base64
import hashlib
from Crypto.Util.Padding import pad, unpad
from Crypto.Cipher import AES

Block_Size = 256
class AESCipher(object):
    def __init__(self, key):
        self.key = hashlib.sha256(key.encode()).digest()

    def encrypt(self, message):
        message = message.encode()
        # raw = pad(message)
        raw = pad(message, Block_Size)
        cipher = AES.new(self.key, AES.MODE_CBC, self.__iv().encode('utf8'))
        enc = cipher.encrypt(raw)
        return base64.b64encode(enc).decode('utf-8')

    def decrypt(self, enc):
        enc = base64.b64decode(enc)
        cipher = AES.new(self.key, AES.MODE_CBC, self.__iv().encode('utf8'))
        dec = cipher.decrypt(enc)
        return unpad(dec, Block_Size).decode('utf-8')

    def __iv(self):
        return chr(0) * 16

    def get_key(self):
        return self.key

    def get_base64_key(self):
        return base64.b64encode(self.key)


if __name__ == '__main__':
    key = "this is key"
    data = "암호화 대상문장 입니다"

    aes = AESCipher(key)

    encrypt = aes.encrypt(data)
    print("암호화:", encrypt)
    print("-" * 100)

    decrypt = aes.decrypt(encrypt)
    print("복호화:", decrypt)
    print("-" * 100)
