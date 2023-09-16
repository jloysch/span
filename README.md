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
