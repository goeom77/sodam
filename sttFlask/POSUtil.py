from kiwipiepy import Kiwi, Match
from kiwipiepy.utils import Stopwords
import collections

kiwi = Kiwi()
stopwords = Stopwords()
kiwi.prepare()
kiwi.add_user_word('소담', tag='NNP', score=0)
kiwi.add_user_word('비대면', tag='NNP', score=0)

def analysis(data):
    result = kiwi.tokenize(data, stopwords=stopwords)

    remove_set = {'EP', 'EF', 'EC', 'ETN', 'ETM', 'NNB'}
    list = [str(tmp.form + '/' + tmp.tag) for tmp in result if tmp.tag not in remove_set]

    counter = collections.Counter(list)
    return counter

# if __name__ == '__main__':
#     print(analysis('나는 초콜릿이 아니다'))
#     print('/' * 100)

