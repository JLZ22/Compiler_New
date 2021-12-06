	.data
	varx: .word 0
	vary: .word 0
	newLine: .asciiz "\n"
	.text
	.globl main
main:
	li $v0 3
	la $t0 varx
	sw $v0 ($t0)
	li $v0 2
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of stack into $v0
	la $t0 varx
	lw $v0 ($t0)
	lw $t0 ($sp)	# pop $t0
	addu $sp $sp 4
	mult $t0, $v0
	mflo $v0
	la $t0 vary
	sw $v0 ($t0)
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of stack into $v0
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)	# pop $t0
	addu $sp $sp 4
	add $v0, $t0, $v0
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	li $v0, 10
	syscall
