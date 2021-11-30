# This program asks the user to input a number and 
# prints Even if the number is even and Odd if the 
# number is odd. 
# 
# @author JOhn Zeng
# 11/19/2021
    .data 
        msg: .asciiz "Please input a number "
        evenMsg: .asciiz "Even"
        oddMsg: .asciiz "Odd"

    .text 0x00400000
    .globl main 
main: 
    la $a0, msg
    li $v0, 4
    syscall         # ask the user to input a number

    li $v0, 5
    syscall
    move $t0, $v0   # store the number into a register

    li $t1, 2
    div $t0, $t1
    mfhi $t1        # divide the number by two and store the remainder into a register

    li $v0, 4

    beq $t1, 0, even 
    la $a0, oddMsg
    syscall             # if the remainder is 0, print even, else odd 
    j end               # terminate the program 

    even:               # print Even
        la $a0, evenMsg
        syscall         
    
    end: 
        li $v0, 10
        syscall 
    