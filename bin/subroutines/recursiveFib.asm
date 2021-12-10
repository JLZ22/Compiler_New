# Asks the user to input an integer and outputs a fibonacci 
# sequaence until it reaches that number . 
#
# @author John Zeng
# @date 12/08/2021
    .data 
        msgFirst: .asciiz "Please input an integer: "

    .text 0x00400000
    .globl main
main: 
    li $v0 4
    la $a0 msgFirst 
    syscall 
    li $v0 5
    syscall 
    move $a3, $v0 