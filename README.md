
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
