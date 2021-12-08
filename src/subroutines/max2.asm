# Asks the user to input two numbers and outputs the 
# greater one. 
#
# @author John Zeng
# @date 12/08/2021
    .data 
        msgFirst: .asciiz "Please input the first number: "
        msgSecond: .asciiz "Please input the second number: "

    .text 0x00400000
    .globl main
main: 
    li $v0 4
    la $a0 msgFirst 
    syscall 
    li $v0 5
    syscall 
    move $t0, $v0

    li $v0 4
    la $a0 msgSecond
    syscall 
    li $v0 5
    syscall

    move $a0, $v0
    move $a1, $t0

    subu $sp $sp 4 
    sw $ra ($sp)
    jal maxTwo
    lw $ra ($sp)
    addu $sp $sp 4
    li $v0 10
    syscall 

maxTwo: 
    blt $a1 $a0 concludeMaxTwo 
    move $a0 $a1
concludeMaxTwo: 
    li $v0 1
    syscall 
    jr $ra 
    