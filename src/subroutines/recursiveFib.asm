# Asks the user to input an integer and outputs a fibonacci 
# sequaence until it reaches that number . 
#
# @author John Zeng
# @date 12/08/2021
	.data
        msgFirst: .asciiz "Please input an integer: "
	    varignore: .word 0
	    newLine: .asciiz "\n"
	.text
	.globl main
main:
    li $v0 4
    la $a0 msgFirst 
    syscall 
    li $v0 5
    syscall 
    beq $v0 0 caseZero

	subu $sp $sp 4
	sw $ra ($sp)	# push val of $ra into the stack
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	jal PROCfib
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
	j end 

PROCfib:
	li $t0 0
	subu $sp $sp 4
	sw $t0 ($sp)	# push val of $t0 into the stack
	addu $t0 $sp 4
	lw $v0 ($t0)
	move $t1, $v0
	li $v0 1
	bgt $t1, $v0, endIf1
	addu $t0 $sp 4
	lw $v0 ($t0)
	addu $t0 $sp 0
	sw $v0 ($t0)

	endIf1: 
	addu $t0 $sp 4
	lw $v0 ($t0)
	move $t1, $v0
	li $v0 1
	ble $t1, $v0, endIf2
	subu $sp $sp 4
	sw $ra ($sp)	# push val of $ra into the stack
	addu $t0 $sp 8
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	li $v0 1
	lw $t2 ($sp)	# pop value of stack into $t2
	addu $sp $sp 4
	sub $v0, $t2, $v0
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	jal PROCfib
	lw $t0 ($sp)	# pop value of stack into $t0
	addu $sp $sp 4
	lw $ra ($sp)	# pop value of stack into $ra
	addu $sp $sp 4
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	subu $sp $sp 4
	sw $ra ($sp)	# push val of $ra into the stack
	addu $t0 $sp 12
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	li $v0 2
	lw $t2 ($sp)	# pop value of stack into $t2
	addu $sp $sp 4
	sub $v0, $t2, $v0
	subu $sp $sp 4
	sw $v0 ($sp)	# push val of $v0 into the stack
	jal PROCfib
	lw $t0 ($sp)	# pop value of stack into $t0
	addu $sp $sp 4
	lw $ra ($sp)	# pop value of stack into $ra
	addu $sp $sp 4
	lw $t2 ($sp)	# pop value of stack into $t2
	addu $sp $sp 4
	add $v0, $t2, $v0
	addu $t0 $sp 0
	sw $v0 ($t0)

	endIf2: 
	lw $v0 ($sp)	# pop value of stack into $v0
	addu $sp $sp 4
	jr $ra

caseZero: 
    li $v0 1
    li $a0 0
    syscall

end: 
    li $v0 10
    syscall
