	# This is auto-generated assembly code which represents
	# a compiled version of simplified pascal. 
	# @author John Zeng
	# @date 2021-12-25
	.data
	varignore: .word 0
	newLine: .asciiz "\n"
	.text
	.globl main
main:
	subu $sp $sp 4
	sw $ra ($sp)	# push val of $ra into the stack
	li $v0 10
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	jal PROCfoo
	li $v0 10
	lw $t0 ($sp)	# pop value of stack into $t0
	addu $sp $sp 4
	lw $ra ($sp)	# pop value of stack into $ra
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	li $v0, 10
	syscall
PROCfoo:
	addu $t0 $sp 0
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	li $v0 1
	lw $t0 ($sp)	# pop value of stack into $t0
	addu $sp $sp 4
	add $v0, $t0, $v0
	addu $t0 $sp 0
	sw $v0 ($t0)
	addu $t0 $sp 0
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	jr $ra
