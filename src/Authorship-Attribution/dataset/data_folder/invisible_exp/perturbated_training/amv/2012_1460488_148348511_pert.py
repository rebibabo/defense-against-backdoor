import sys
 
 oqnzt = dict()
 
 def learn(szwpx, dapnc):
     for ajpek, clear_letter in zip(szwpx, dapnc):
         if ajpek in oqnzt:
             assert oqnzt[ajpek] == clear_letter
         oqnzt[ajpek] = clear_letter
 
 def test():
     alphabet = 'abcdefghijklmnopqrstuvwxyz '
     for letter in alphabet:
         if letter not in oqnzt.keys():
             print 'Googlerese letter %s does not have corresponding English letter.' % letter
         if letter not in oqnzt.values():
             print 'English letter %s not found in oqnzt dictionary values.' % letter
     if len(oqnzt.keys()) != len(alphabet):
         print 'Size of oqnzt dictionary and alphabet mismatch.'
 
 def decrypt(text):
     decrypted_text = ''
     for letter in text:
         assert letter in oqnzt
         decrypted_text += oqnzt[letter]
     return decrypted_text
 
 def main():
     learn(' yeqz', ' aozq')
     learn('ejp mysljylc kd kxveddknmc re jsicpdrysi', 'our language is impossible to understand')
     learn('rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd', 'there are twenty six factorial possibilities')
     learn('de kr kd eoya kw aej tysr re ujdr lkgc jv', 'so it is okay if you want to just give up')
     test()
 
     case_count = int(sys.stdin.readline())
 
     for case_index in range(1, case_count + 1):
         print 'Case #%i: %s' % (case_index, decrypt(sys.stdin.readline().strip()))
 
 if __name__ == '__main__':
     main()