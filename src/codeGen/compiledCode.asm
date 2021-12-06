	.data
	newLine: .asciiz "\n"
	.text
	.globl main
main:
	li $v0 14
	move $t0, $v0
	li $v0 14
	bne $t0, $v0, endif1
	li $v0 14
	move $t0, $v0
	li $v0 14
	beq $t0, $v0, endif2
	li $v0 3
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	endif2: # in place to skip the instructionsfollowing the if statement in the case that the condition is false
	li $v0 14
	move $t0, $v0
	li $v0 14
	bge $t0, $v0, endif3
	li $v0 4
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	endif3: # in place to skip the instructionsfollowing the if statement in the case that the condition is false
	endif1: # in place to skip the instructionsfollowing the if statement in the case that the condition is false
	li $v0 15
	move $t0, $v0
	li $v0 14
	blt $t0, $v0, endif4
	li $v0 5
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	endif4: # in place to skip the instructionsfollowing the if statement in the case that the condition is false
	li $v0, 10
	syscall
