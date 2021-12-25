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
	sw $ra ($sp)	# push val of stack into $ra
	li $v0 8
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of stack into $v0
	li $v0 9
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of stack into $v0
	li $v0 10
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of stack into $v0
	jal PROCfoo
	li $v0 8
	lw $t0 ($sp)	# pop $t0
	addu $sp $sp 4
	li $v0 9
	lw $t0 ($sp)	# pop $t0
	addu $sp $sp 4
	li $v0 10
	lw $t0 ($sp)	# pop $t0
	addu $sp $sp 4
	lw $ra ($sp)	# pop $ra
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	li $v0, 10
	syscall
PROCfoo:
	jr $ra
