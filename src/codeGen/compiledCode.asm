	# This is auto-generated assembly code which represents
	# a compiled version of simplified pascal. 
	# @author John Zeng
	# @date 2021-12-25
	.data
	newLine: .asciiz "\n"
	.text
	.globl main
main:
	subu $sp $sp 4
	sw $ra ($sp)	# push val of $ra into the stack
	li $v0 5
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	li $v0 12
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	jal PROCmax
	lw $t0 ($sp)	# pop value of stack into $t0
	addu $sp $sp 4
	lw $t0 ($sp)	# pop value of stack into $t0
	addu $sp $sp 4
	lw $ra ($sp)	# pop value of stack into $ra
	addu $sp $sp 4
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	subu $sp $sp 4
	sw $ra ($sp)	# push val of $ra into the stack
	li $v0 13
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	li $v0 7
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	jal PROCmax
	lw $t0 ($sp)	# pop value of stack into $t0
	addu $sp $sp 4
	lw $t0 ($sp)	# pop value of stack into $t0
	addu $sp $sp 4
	lw $ra ($sp)	# pop value of stack into $ra
	addu $sp $sp 4
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	li $v0, 10
	syscall
PROCmax:
	li $t0 0
	subu $sp $sp 4
	sw $t0 ($sp)	# push val of $t0 into the stack
	addu $t0 $sp 8
	lw $v0 ($t0)
	addu $t0 $sp 0
	sw $v0 ($t0)
	addu $t0 $sp 4
	lw $v0 ($t0)
	move $t1, $v0
	addu $t0 $sp 8
	lw $v0 ($t0)
	ble $t1, $v0, endIf1
	addu $t0 $sp 4
	lw $v0 ($t0)
	addu $t0 $sp 0
	sw $v0 ($t0)
	endIf1: 
	lw $v0 ($sp)	# pop value of stack into $v0
	addu $sp $sp 4
	jr $ra
