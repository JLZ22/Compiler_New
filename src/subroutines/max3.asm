# Asks the user to input three numbers and outputs the 
# greater one. 
#
# @author John Zeng
# @date 12/08/2021
    .data 
        msgFirst: .asciiz "Please input the first number: "
        msgSecond: .asciiz "Please input the second number: "
        msgThird: .asciiz "Please input the third number: "

    .text 0x00400000
    .globl main
main: 
    li $v0 4
    la $a0 msgFirst 
    syscall 
    li $v0 5
    syscall 
    move $a1, $v0

    li $v0 4
    la $a0 msgSecond
    syscall 
    li $v0 5
    syscall
    move $a2, $v0 

    li $v0 4
    la $a0 msgThird
    syscall 
    li $v0 5
    syscall
    move $a3, $v0 

    subu $sp $sp 4 
    sw $ra ($sp)
    jal maxThree
    lw $ra ($sp)
    addu $sp $sp 4
    li $v0 10
    syscall 

maxThree:   # compare the first two numbers
    blt $a2 $a1 compareNext 
    move $a1 $a2
compareNext: # compare the larger number between the first two and the third 
    bgt $a1 $a3 finishMaxThree
    move $a1 $a3 
finishMaxThree: # print the largest number 
    li $v0 1
    move $a0 $a1
    syscall 
    jr $ra 
    