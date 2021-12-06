	.data
	varcount: .word 0
	newLine: .asciiz "\n"
	.text
	.globl main
main:
	li $v0 1
	la $t0 varcount
	sw $v0 ($t0)
loop1:
	la $t0 varcount
	lw $v0 ($t0)
	move $t0, $v0
	li $v0 15
	bgt $t0, $v0, endWhile1
	la $t0 varcount
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	la $t0 varcount
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of stack into $v0
	li $v0 1
	lw $t0 ($sp)	# pop $t0
	addu $sp $sp 4
	add $v0, $t0, $v0
	la $t0 varcount
	sw $v0 ($t0)
	j loop1
endWhile1:
	li $v0, 10
	syscall
