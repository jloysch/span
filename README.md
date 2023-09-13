> V2! - NOW HIDING PHRASES INSIDE OF A BLOCK OF 256!
>
> LATER - Planning to Daisy-Chain/Cascade multiple SPANS for each character of a phrase to add further complexity and safety. e.g. HELLLO BECOMES 23.2308Asdf...99.9843B.. and so on, each previous block having a key for the next block and the resulting blocks having start/end and ratios as functions of
> each previous block. Planning to implement this exciting idea later.
>
> I imagine a phrase such as password spread out across 8 spans would be hard to find, you would need to know the degree, the ratio, the start/end to the first block, AND what you're looking for to be sure. This is just for the first one if we shuffle. If we take this up a notch and
> chain SPANs then we can add extreme complexity from a simple password encrypted in this medium- there will be a sea of data to look at and it's all plaintext. I would love to add hotwords in the pre/postamble (see website for terminology) to attract would-be attackers to believing they've decrypted the blocks
> when in fact they haven't.

Introducing SPAN: My First Encryption Algorithm - Joshua Loysch

SPAN is a cool little encryption algorithm that takes a bit of a different approach to encrypting data. 

You can enter a plaintext phrase such as hello and receive a cipher. Working example:

> 'hello'

In this system, 'hello' can be expressed as '143.1871A78.62515A263.7692A140.9315A3.4798527A115.6556A40.83441A266.25964A134.09538A310.7332A' with the decoder being 59.62687degsR\\0.888\\8

THERE ARE *MULTIPLE* ways to represent the same expression as well.

THERE ARE *BOTH* Reversible and Irreversible ciphers this algorithm can generate.

This is the first working version, a little idea I have had for a while that I wanted to get coded up. I am excited to see how this goes :)

Theoretically this could have a complexity of 2^129 + 3.194e38 so it's surprisingly secure for such a simple approach. 
