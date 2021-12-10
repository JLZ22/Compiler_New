	# This is auto-generated assembly code which represents
	# a compiled version of simplified pascal. 
	# @author John Zeng
	# @date 2021-12-10
	.data
	newLine: .asciiz "\n"
	.text
	.globl main
main:
	li $v0, 10
	syscall
PROCfoo:
	li $v0 1
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	jr $ra
