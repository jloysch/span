Introducing SPAN: Toy Encryption Algorithm - Joshua Loysch. 256 Block Sea of Encrypted Data 
----------------------------

A MESSAGE FROM ME, TO YOU, IN SPAN! >>

22.49288A208.361A125.34328A302.2945A231.58925A118.4141A272.7725A235.48795A110.51301A322.59674A231.1007A107.13131A311.90915A213.44711A82.600555A306.57126A207.29927A77.41028A262.55197A209.9239A70.66946A285.57553A198.28004A74.38046A267.81967A162.10773A60.433136A243.1833A191.83537A57.919056A257.3956A174.32812A49.419678A242.75708A148.6469A47.47878A267.29514A155.85562A34.528522A263.66116A141.4125A27.899652A218.37434A140.77869A24.419352A236.7149A125.81368A19.597912A237.22234A133.23267A10.154279A225.19093A121.07049A4.3463345A223.30247A127.887344A281.4967A188.45927A109.691826A277.14587A209.38795A117.88703A275.4583A210.60777A103.42007A311.1738A199.01717A103.42007A321.73056A191.12103A99.203156A253.93065A186.59325A91.453415A277.35324A188.2944A87.4453A302.17722A152.43964A68.892746A267.00244A161.70355A64.53893A292.94995A138.40938A58.16363A275.2238A134.25534A53.240242A237.19951A153.58444A45.80231A236.55911A151.86957A40.433395A266.49762A140.07983A35.613045A249.57016A118.46944A27.100796A239.39742A105.91174A21.909615A198.65627A104.25277A12.493749A225.49776A111.73168A6.609652A192.32205A97.642685A0.4145781A200.26477A103.06373A331.71872A181.71422A94.23317A312.7748A201.28467A74.73547A318.4928A183.02509A80.55317A257.43036A187.22198A12.567008A297.3277A192.32921A277.01212A289.478A160.72383A211.69872A301.77304A160.72383A93.08995A273.86115A182.01778A32.84454A249.16626A154.91301A311.4205A234.66997A157.17812A227.8143A255.32872A145.38503A163.12291A238.49709A155.7147A77.4398A219.88957A138.92014A15.911621A223.54088A136.94658A251.90543A245.80304A120.14662A213.58757A217.05542A124.2011A138.66963A239.77544A120.15894A80.02695A239.38593A100.921455A321.06305A198.5346A103.13094A192.69368A205.94414A94.44335A109.131325A181.93031A81.48658A121.63871A204.37701A68.77857A44.17119A196.26392A67.17776A183.50443A166.9391A60.17916A132.56285A158.10077A57.34701A168.02882A182.99905A53.210373A86.52425A168.89185A39.829422A14.601477A141.19179A31.96287A234.2615A132.77875A27.349874A234.2615A149.21704A24.377724A191.30511A126.46924A15.13352A101.69151A135.92574A8.670172A235.42441A122.48967A2.484326A197.9004A103.33358A288.53305A211.58752A111.73091A308.849A196.369A115.61233A331.01434A201.42674A107.50618A266.82574A209.27434A92.860825A291.116A194.24083A79.26434A249.91763A168.27716A84.46874A255.49759A183.05829A72.79122A255.67726A153.72746A71.374504A253.1729A162.68924A51.379562A267.2145A146.53032A52.101013A273.4035A148.7085A49.075436A235.5796A156.63853A40.07181A219.231A129.87169A30.801025A242.69095A143.8292A

//35.23407D0.569R187S214F\\\\

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

> V2! - NOW HIDING PHRASES INSIDE OF A BLOCK OF 256!
>
> LATER - Planning to Daisy-Chain/Cascade multiple SPANS for each character of a phrase to add further complexity and safety. e.g. HELLLO BECOMES 23.2308Asdf...99.9843B.. and so on, each previous block having a key for the next block and the resulting blocks having start/end and ratios as functions of
> each previous block. Planning to implement this exciting idea later.
>
> I imagine a phrase such as 'password' spread out across 8 spans (p)..(a).. would be hard to find, you would need to know the degree, the ratio, the start/end to the first block, AND what you're looking for to be sure. This is just for one SPAN if it's shuffled internally sufficiently.

If we take this up a notch and
> chain SPANs then we can add extreme complexity from a simple password encrypted in this medium- there will be a sea of data to look at and it's all plaintext. I would love to add hotwords in the pre/postamble (see website for terminology) to dupe would-be attackers to believing they've decrypted the blocks
> when in fact they haven't.


RECOGNIZED CHARACTERS
> abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~`!@#$%^&*()_-+=



Introducing SPAN: My First Encryption Algorithm - Joshua Loysch

SPAN is a cool little encryption algorithm that takes a bit of a different approach to encrypting data. 

You can enter a plaintext phrase such as hello and receive a cipher. Working example:

> 'hello'

In this system, 'hello' can be expressed as '143.1871A78.62515A263.7692A140.9315A3.4798527A115.6556A40.83441A266.25964A134.09538A310.7332A' with the decoder being 59.62687degsR\\0.888\\8

THERE ARE *MULTIPLE* ways to represent the same expression as well.

THERE ARE *BOTH* Reversible and Irreversible ciphers this algorithm can generate.

This is the first working version, a little idea I have had for a while that I wanted to get coded up. I am excited to see how this goes :)

> This algorithm should have a calculated complexity of 2^54 for the key. Or a possible complexity of (2^140 + (1.3^46)) (Without Daisy-Chaining (to be added)) and (2^158 + 1.3^46) with the Daisy-Chaining (to be added later) when considering multiple possible ciphers for the data.

The attacker must know what they are looking for in order to execute an effective brute force. A decryption of the block with the wrong key may yield something that very much seems what like they're looking for but ultimately isn't it.

Key size is >= 2^54 (Depending on precision, will be adressed)

At a rate of 2^32 calculations/s for bruteforce it would take about 4.25yrs to generate the correct key. The attacker would then need the context or some clue to as to what the phrase actually was. E.g. a password of 'Password1@!' may be strewn somewhere in the block. If the attacker believes they have the right key
then they would receive a phrase that they believe to br whatever it is they're looking for. This may or may not be the case. The attacker would need to keep track of every generated possibility and run it against some dictionary to generate the most likely keys. This would take an uncalculated amount of time, including permutations and an unknown length as well as other factors. A user will have changed their password by then.

SPAN's intent is to encrypt data quickly and safely while not being too costly. If you store some secret in there such as a password, and the attacker manages to get your cipher and not the key, then that could actually be useless; what they're left with is possible passwords you could've used that is as good as their likely keys they've generated. SPAN would allow data to be intercepted and still be safe since the attacker can't prove for sure what was encrypted with SPAN unless they plug that secret data into whatever resource they're trying to access.

-------------------------

DEBUG OUTPUT 9/16/2023

> 'HELLO' > 716805 unique, 2109887 bad blocks, 0 collisions, 3 bad generations, run 2826692 @ 25.358442% find rate. | t=3680.911s

I have an algorithm to see how many unique generations there are for a particular phrase. For 'HELLO' with a static ratio there are at least 716,805 unique generations (stopped due to laptop limitations) with 0 ciphers repeated during this test and 3 bad ciphers generated (errored generations / erroneous)
There is about a 1 in 4 chance of finding a block that will work so the algorithm runs until it verifies the block and returns it to you (about 4 times max per legitamate generation)


When you brute force the block in SPAN, you get triangles!

BRUTE FORCE OUTPUT >

 
  
   
   m
D    
"     
      t
"       
"     3  
J     t   
J     t   ^
"        f^ 
"       o    
@  7 %4   ^!K 
      t o      
"       o ^     
"       o   u    
"     3      Q    
"       o ^        
              U     
"       o   u        
#                     
"       o ^            
      t          7      
 @ m   _    w            
"                    5I G 
@  7 %4    !K      kD      
          ^ n *     /e    . 
 x    e   s    d 2           
"       o            ^        
"           u  9     ^  6    VA
J     t   ^ n    3  /       % A 
"           u  9             V   
"       o   u        ^            
   m     E     V 2 !          A    
"       o       V             e     
"         ^       Z        5         
                   R          A     A 
"                       G              
          ^         /e     Qr e (      .
                       _      A o 4      
 @Tm   _   [w            S          P  g  
"       o               G              *   
J     t   ^ n    3  /     .   b    ;  z.    
      t          7          e A              
"                       G              6      
h  7       !K        -   4             3       
      O_  G    w              A     R           
      t          7          e A                 F
"        f^            U      A 6                 
              '                   A         9L    S
   7        K        -  G4             3          M 
d     t             /   &              .Q      5     
d     t             /   &              .Q         s   
   m   _o1 Ow      R     S             g               
"        f^       Z   QU  4                 9           
"       of^       Z   QU   5      [    Q             Z   
      t          7          e A                           
                         O             g          M  w    B
"       o ^                   A                5  s       B 
   7        K        -  G4             3          M  z       
d     t             /   &    g         .Q      5              
"       o   u        ^  6     A                   s       B    
          ^        R/~     Qr e     J  .       5     #          
 9 m~    E3    V 2 R     3             c          I             K
      t o          Re                          5  s          T  X 
"         ^                5    !      S             b          4  
"         ^       Z        5  A        Q          s  Z    B         
            K            O             g          M  w          Z  % 
J     t   ^ n    3  /       % A       z.             #       T     w  
"                          5           S             b          4      
 x        s      2 !                   0                  B             
"       o          R          A                5  s       B  T  Z        
                   R          A                5  M       B  T  Z         
      t o ^ n    3 R/e    .   A      I .             #          J          
#                        O             g          M  w          Z           
"         ^       Z        5  A        Q          s  Z          L            
"         ^       Z        5  A        Q          s  Z    B                   
J     t   ^ n    3  /     .   b    ;  z.      @      #          J          1  v
"                       G              6                        C  y          L 
@     4     K            O             g          M  w          Z  %          V  
 x}       s    d 2 !                   0                                   G  -   
      t   ^        R/                  .       5  s  #                             
            4      R          A                5  M          T  Z          1  V     
"     3 o   ]      RO5    O   A        =       5  s  r    B  T     !       1  v      
"         ^                5           S             b          4             Q       
Y           4 m  a           R            c       s  -Q                                
"         ^                5           S             b          4             Q         
   m   e    w   d2       )             g          s                        G  v          
d     t       *     /   &    g        04             i          X  y          2           
"       o               G              *          s  e             w          v            
"           u  9        6    V                       ]          4  ;          Q             
@  7 %4 o ^!K      kD    4             3          M          T  Z  %          V             0
                   R                   {       5  M  0       T  Z          1  V          }  0 
        o ^                            g+      5  s  #       T             1  D          }  2  
          ^   *    R/e    .   e        .             #          J             L          }  d   
          G    w                    R             M          G  W          {  ;          c  w    
 9 m     E3 w    2       3             +          I             K             [             0     
        o          R          A                5  M       B  T  Z          1  V          }  0      
      t   ^        R/                  .       5  s  #       T                v          K  w       
"       o   u        ^                            s                           v             w       C
h  7 %4   ^!K      kD    4             3          M  z          Z          F  V          Q  0         
   7           "   R -  G              2       5  M             Z             V             0          
d     t       *     /   &    g        04          s  i          X             2             s  `       V
"                    ^    i            =             r          C  !                        Z       A  Y 
"           u  9           5           S             b          4             Q             6          Y  
"     3             O     i            =             r          T  !          Z             8       A  Y  _
        o ^                            g+      5  s  #       T             1  v          }  2          V  [ 
          ^         /e     Qr e (      .            H#                        L             d                
           6   w      F  e                                      W                                      .  @   
"        f^       Z   QU  4       [         9                   L  c          2             )          S  "    
"       of^       Z   QU   5      [    Q             Z          L             2          }  -          S  n     
"       o ^                            R       5  s  a       T             1  v          }  w          V         
   m        w    2 R          A                5  H          T  X          1  v          }  w          V          
d     t       *     /   &    g         4             i          X  y          2             s          V  3        
J     t   ^ n    3  /         b    ;  z.             #                        P  `          T             X         
Y       o   4 m  a           RA                5  s  -                                      R  ~       V          H  
"       o       V             e        Q          s  a                        v          B  w       C                U
   m~    E     V 2 !                              Q             K             c          N  s          V             Y 
          G    w         ^                     5  M             W             ;          5  z          {          [  2  
      t            R                     p    b5          B     X             v             w       C  J             8  z
"       o   u        ^                            s       B                   v             w       C  Y             g  z 
"     3     ]      RO     O   A        =       5     r    B  T  T  !       1  v          }  w       C  V             W  z  
   m        w    2 R          A                5  s          T             1  v          }  w       C  .             f  z   
"       o   u        ^   u             /          s  l                        v             w       A  Y          J  g  z    
Y           4 m  a           RA           c       s  -Q                                     R  ~       V          H     V     
 @ m   _ I  w      R     S          G  g                        a                        M  l       B  d             +  z      
                   R          A     A          5  M       B  T  Z          1  V          }  0       B  V             W          
      t o           /                  .                                   1  2             s          M  $          O  t        
      t o           e        f         4   2   5  s  1   &   T  X  g       1  2  *       }  =       C  V  0          8          D 
   m   e    w   d2 R     )             g          s                        G  v          M  w       C  .             f          D  
 9 m~    E     V 2 R                   c          I             K             c          }  s                        Y             r
"                    ^                 =             r          C  !                        Z       A  Y  3       J  g  z       N  ( 
D     t o     A     e        f         4   2   5  s  1   &   T  X  g       1  2  *       }  =          V  0          8  o       D  7  
"     3 o   ]Q     RO5    O   A        =       5  s  r    B  T     !       1  v          }  w       C  V             W  z       D     `
J     t   ^      3  /         A        .       5     #       T     w       1  v  `       K  w             $          W          D       
    n   o      w   R          A     }             s          T  W                           w          V             Y          }  s  `  
"       o ^                            g       5  s  a       T             1  v          }  w          V  [       H  W  0                 
"       o   u        ^  6     A                   s       B                   S             W       C  Y  n          g          D  (       
@     4     K      RD                  g          M  w          Z  %          V             m          X             W          L     `     
"       o   u        ^                            s                           v             w       C  Y             g  z       D  (  `      
"     3 o   ]      RO5    O   A        =       5  s  r    B  T     !       1  v          }  w       C  V             W  z       D     `       
          ^ n       /e    .          I .       5     #          J             L             d             U          W  z       D              
"     3             O     i            =             r          T  !          Z             8       A  Y  _       J  g          N  (  z    F    
"         ^                5           S                        4             Q             w          Y             W  z       D     `          
 9 m~    E     V 2 R                   c          I             K             c          }  s                        Y             r          V   
      t o          R                           5  s       B  T  X          1  2          }  R       C  V             8  z       D  7  `          / 
"       o   u        ^                            s                           v             w       A  Y          F  g  z       O  (  `       G     
          ^   *    R/e    .   e        .             #          J             L          }  d                                      V                 
"     3            RO5I G              6                        4  y          2             l  \       N  l          .          C  d       I     p    
"        f^       Z   QU   5      [    Q             Z          L  c          2             -       C  S  n          6  u       D  g  z          0     
"       o               G              *          s  e             w          v             w  |       N             .  z       D          I     9      
"     3             O                  =             r          T  !          Z             8       A  Y  _       J  g          N  (  z    F             
                   R          A     A          5  M       B  T  Z          1  V          }  0       C  V             W          D             7  2        
          ^        R/~     Qr e     J  .       5     #                        K                                   H             D                          
#    le_           !     O             g       5  M          T  Z             i          C  _          V  &          8  z       L  7  `       8     #       
"       o       V             e        *          s  a                        v          B  w                        U          D          I     9       M  E
 x}       s    d 2 !                   0                                   G  -          T  k          V             }             8          .  x          3 
 x        s      2 !                   0                  B                1  v          N  s                        }             8             2  #          
      t o          R/                  .                                   1  2          }  s          M  $          O  t       H     `          2  @       E  z
 x ma     s      2 !                   0                                   1  c          N  s                        }             8  `          2  #    J     % 
"       o   u        ^   u             /          s  l                        v             w       A  Y          J  g  z       N  (  `             #          ~  
"         ^       Z        5                                                  v             w       C  Y             }  u       J     z       F  2          Z  Y   
"     3             O                  =             r          T  !          Z             8       A  Y  _       J  g          N  (  z    F                   ~    
"                       G              6                        C  y          L             d  \       N          I  .  z       O  d  `    I  S  p  #    M  8  l  !  
"     3     ]       O     O            =             r          T  !          Z             8             _                     D  9  z          0          E  x      
"                    5I G              6                           y          v             w  \       N             .  z       C  d  `    I     p  #    M     l  !    
      t   ^      3 R/         A        .       5  s  #       T     w          v          K  w       C     $                     D                2  `          Y  !     
        o  $   w      F  e    A     /                        T                           2  '          .  @    I     W          Y             f     #       i            
"        f^       Z   QU  4                 9                                               )       C  S  "          6  ~       D  g       A     0          /  i  !    L  
 x    e   s    d 2 R          A                   M       B                   v          C  w          V             W  z       L          G     2       D  E  Y    I      
      t       U  7 R            o          Dc    ;        V     X  )                        Q       C  J                           S          I  {          8  X            
"         ^                5                                    4             v             w       C  Y             W  z       D     `          2  #       E  Y    B     [  
          ^         /e     Qr e (      .            H#                        K             d                     H             C  V                        E  f       N     o
 9 m     E3 w    2       7             +          I             K             [             0       C  V  v       H  W             ]          ]             {  %  !       l    
      t       X     /              T  #.O          (               {          U             s             $          O          H  T  @          W          E  z       E        
"           u  9     ^  6    VA                      ]    B     4  ;          Q             6          Y  m          g          D  (                     G  E  ~    B  R         
 x    e   s    dd2 !                   3          s                           v          }  k          V             W          L          G     x       D  E  v    I     b    A  
 x    e   s    d 2            A                   M                           v          }  w          V             W  #       L          G     2       D  E  v    I          A  H
"       of^       Z   QU   5           Q             Z          L             2          }  -          S  n          6  u       D  g  z          0  #       E  i       F            
      t o     X     /            # T  #.O                          {          U             s             $                     H  T  @          W          E  z       E             
    n          w              A                5  s       B  T  W             v          }  w       C  V             Y  z       }     `          2       N  E  '  !          Z        
      t          7            A                 F               X  b                        A          J  U          8  7          B          I             8                          
"                       G              6                        C  w          v             w  |       N          I  .  z       O  d  `    I  S  p  #    M  8  l          0       H  U  
d     t       *     /   &    g        04          s  i          X             2             s  `       V  3          W  z          7             s  `       E  q    D        m       L  2
   m   _o1 Ow      R  A  S             g                     A                v          }  r       C  V                #       D  o  `       .  2  #       3  Y  \    K     Z       }  F 
        o ^                            g+      5  s  #       T             1  D          }  2          V  ;       H  W  ;                  A  7  2          E  Y       M     Z       L  F  
Exa   e  a     d 23R     8    A                   M       B                   c          C  w          V             g  z       L     `    G  8  2  #    D     Y             Z    H     F  [
Y           4    a  6        RA                              T     c             y                  C  V          H  T                           2       L     X             Z          F  " 
J     t   ^ n    3  /         b    ;  z.             #                        P  `          T             $          W          A                           E  8       N     x    A     ]  @  
"        f^       Z   QU  4       [                             L  c          2             -          S  "          6  ~       D  g       A     0          /  i       L  j             =      
 @ m   _   [w            S          P  g          s          A  P       A                M             {             W  #       {          E     k    E  5  3                w    K     q       
Y           4 m  a           RA           c    5  s  -Q                t                    R  ~       V          H     z       P  0             2       L     Y       P     Z          e  "     
          ^         /e        e (      .       5    H#                        L             d                     H             C  V                           f       N  8  o  |    H  )  z    H 
        o  x                  A                5             T             1  v             w       C  c       A     W       F  D                2  #          Y  !                     F  [       
"         ^                5    !      S             b          4             Q             6          Y          E  g  z       D     `          2  #       E  Y  ! B        Z          F  [        
"     3     ]       O     O            =             r          T  !          Z             8       C     _                     D  9  z          0          E  x             ^          F            
C                  R   e           9    .                 B                1  v       2             C  V  M          8  z          D  `          2          ]  f  !          r       W  F  [          
   m        w    2 R                           5  s          T             1  v          }  w       C  .             f  z       D  ]  `          2  #       E  Y  !    S     Z       U  F  [           
"           u  9        6    V                       ]          4  ;          Q             6          Y  m          g          D  (                     G  E  ~    B  R  [             h          N  0 
                   R                   {       5  M  0       T  Z          1  V          }  0          V          B  W                           2          5  Y  !       8  Z          8  [ D           
"           u              5           S             b          4             Q             6          Y  m       E  g          H  (          }  {  #    G        ! B  R  [             h          N  0   
"       o       V             e        *          s  a                        v          B  w                        U          D          I     9       M  E  n                     U  F  w    H        ~ 
"       o      X              A                   s       B                A  v          B  0       C                           D  7       I     s       M  E  u                     U  F                {  
"
  
"  
h  m
"    
8     
h  m   
"       
"        
          
   m       
   m  e_o   
"       o ^  
q       o     
      3        
h  m     l      
                 
      t o ^      7
      3         (  
Example_Password123!
