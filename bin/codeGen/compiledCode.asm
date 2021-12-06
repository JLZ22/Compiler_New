	# This is auto-generated assembly code which represents
	# a compiled version of simplified pascal. 
	# @author John Zeng
	# @date 2021-12-06
	.data
	varx: .word 0
	vary: .word 0
	varcount: .word 0
	newLine: .asciiz "\n"
	.text
	.globl main
main:
	li $v0 2
	la $t0 varx
	sw $v0 ($t0)
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of stack into $v0
	li $v0 1
	lw $t0 ($sp)	# pop $t0
	addu $sp $sp 4
	add $v0, $t0, $v0
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
	la $t0 varx
	sw $v0 ($t0)
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of stack into $v0
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)	# pop $t0
	addu $sp $sp 4
	mult $t0, $v0
	mflo $v0
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	la $t0 varx
	lw $v0 ($t0)
	move $t0, $v0
	la $t0 vary
	lw $v0 ($t0)
	ble $t0, $v0, endIf1
	la $t0 varx
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	la $t0 vary
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	endIf1: 
	li $v0 14
	move $t0, $v0
	li $v0 14
	bne $t0, $v0, endIf2
	li $v0 14
	move $t0, $v0
	li $v0 14
	beq $t0, $v0, endIf3
	li $v0 3
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	endIf3: 
	li $v0 14
	move $t0, $v0
	li $v0 14
	bgt $t0, $v0, endIf4
	li $v0 4
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	endIf4: 
	endIf2: 
	li $v0 15
	move $t0, $v0
	li $v0 14
	ble $t0, $v0, endIf5
	li $v0 5
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	endIf5: 
	li $v0 1
	la $t0 varcount
	sw $v0 ($t0)
loop6:
	la $t0 varcount
	lw $v0 ($t0)
	move $t0, $v0
	li $v0 15
	bgt $t0, $v0, endWhile6
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
	j loop6
endWhile6:
	li $v0, 10
	syscall
