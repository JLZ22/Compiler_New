	.data
	newLine: .asciiz "\n"
	.text
	.globl main
main:
	li $v0 0
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of stack into $v0
	li $v0 3
	lw $t0 ($sp)	# pop $t0
	addu $sp $sp 4
	sub $v0, $t0, $v0
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of stack into $v0
	li $v0 11
	lw $t0 ($sp)	# pop $t0
	addu $sp $sp 4
	add $v0, $t0, $v0
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	li $v0 16
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of stack into $v0
	li $v0 2
	lw $t0 ($sp)	# pop $t0
	addu $sp $sp 4
	div $t0, $v0
	mflo $v0
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	li $v0, 10
	syscall
