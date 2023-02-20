import base64
import hashlib
from Crypto.Util.Padding import pad, unpad
from Crypto.Cipher import AES
import os

Block_Size = 256
class AESCipher(object):
    def __init__(self, key):
        print('init')
        print(key)
        print(key.encode())
        self.key = hashlib.sha256(key.encode()).digest()
        print('end')

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
        strkey = ''.join('{:02X}'.format(a) for a in self.key)
        bytekey = strkey[0:32].encode()
        print(type(bytekey))
        print(bytekey.__len__())
        return base64.b64encode(bytekey)


if __name__ == '__main__':
    key = "mykey"
    data = "암호화 대상문장 입니다"

    aes = AESCipher(key)

    print(aes.get_key())
    print(aes.get_base64_key())
    print(aes.get_base64_key().decode('utf-8'))

    encrypt = aes.encrypt(data)
    print("암호화:", encrypt)
    print("-" * 100)

    decrypt = aes.decrypt(encrypt)
    print("복호화:", decrypt)
    print("-" * 100)

    base64_key = aes.get_base64_key()
    print(aes.get_key())
    print(base64_key)
    print(base64_key.decode('utf-8'))

