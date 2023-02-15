from konlpy.tag import Okt
import collections

okt = Okt()

def analysis(data):
    result = okt.pos(data, stem=True, join=False)

    add_set = {'Noun', 'Adjective', 'Exclamation', 'Adverb'}
    list = [str(tmp[0] + '/' + tmp[1]) for tmp in result if tmp[1] in add_set]

    counter = collections.Counter(list)
    return counter

