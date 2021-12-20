	# This is auto-generated assembly code which represents
	# a compiled version of simplified pascal. 
	# @author John Zeng
	# @date 2021-12-20
	.data
	varignore: .word 0
	newLine: .asciiz "\n"
	.text
	.globl main
main:
	subu $sp $sp 4
	sw $ra ($sp)	# push val of stack into $ra
	jal PROCfoo
	lw $ra ($sp)	# pop $ra
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	li $v0, 10
	syscall
PROCfoo:
	subu $sp $sp 4
	sw $ra ($sp)	# push val of stack into $ra
	jal PROCbar
	lw $ra ($sp)	# pop $ra
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	li $v0 3
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	jr $ra
PROCbar:
	li $v0 0
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of stack into $v0
	li $v0 3
	lw $t0 ($sp)	# pop $t0
	addu $sp $sp 4
	sub $v0, $t0, $v0
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	jr $ra
