	# This is auto-generated assembly code which represents
	# a compiled version of simplified pascal. 
	# @author John Zeng
	# @date 2021-12-30
	.data
	varcount: .word 0
	varignore: .word 0
	vartimes: .word 0
	newLine: .asciiz "\n"
	.text
	.globl main
main:
	li $v0 196
	la $t0 varcount
	sw $v0 ($t0)
	li $v0 0
	la $t0 vartimes
	sw $v0 ($t0)
	subu $sp $sp 4
	sw $ra ($sp)	# push val of $ra into the stack
	li $v0 10
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	li $v0 13
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	jal PROCprintSquares
	lw $t0 ($sp)	# pop value of stack into $t0
	addu $sp $sp 4
	lw $t0 ($sp)	# pop value of stack into $t0
	addu $sp $sp 4
	lw $ra ($sp)	# pop value of stack into $ra
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	la $t0 varcount
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	la $t0 vartimes
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	li $v0, 10
	syscall
PROCprintSquares:
	li $t0 0
	subu $sp $sp 4
	sw $t0 ($sp)	# push val of $t0 into the stack
	li $v0 0
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	li $v0 0
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	addu $t0 $sp 24
	lw $v0 ($t0)
	addu $t0 $sp 16
	sw $v0 ($t0)
loop1:
	addu $t0 $sp 16
	lw $v0 ($t0)
	move $t1, $v0
	addu $t0 $sp 20
	lw $v0 ($t0)
	bgt $t1, $v0, endWhile1
	addu $t0 $sp 16
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	addu $t0 $sp 20
	lw $v0 ($t0)
	lw $t2 ($sp)	# pop value of stack into $t2
	addu $sp $sp 4
	mult $t2, $v0
	mflo $v0
	addu $t0 $sp 12
	sw $v0 ($t0)
	addu $t0 $sp 12
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	addu $t0 $sp 16
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	li $v0 1
	lw $t2 ($sp)	# pop value of stack into $t2
	addu $sp $sp 4
	add $v0, $t2, $v0
	addu $t0 $sp 16
	sw $v0 ($t0)
	la $t0 vartimes
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	li $v0 1
	lw $t2 ($sp)	# pop value of stack into $t2
	addu $sp $sp 4
	add $v0, $t2, $v0
	la $t0 vartimes
	sw $v0 ($t0)
	j loop1
endWhile1:
	lw $t0 ($sp)	# pop value of stack into $t0
	addu $sp $sp 4
	lw $t0 ($sp)	# pop value of stack into $t0
	addu $sp $sp 4
	lw $v0 ($sp)	# pop value of stack into $v0
	addu $sp $sp 4
	jr $ra
