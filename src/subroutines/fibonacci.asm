# Asks the user to input an integer and outputs a fibonacci 
# sequaence until it raches that number . 
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

    ble $a1 1 baseCase

    subu $sp $sp 4 
    sw $ra ($sp)
    li $a1 0
    li $a0 1
    jal fib
    lw $ra ($sp)
    addu $sp $sp 4
    li $v0 10
    syscall 

fib: 
    ble $a3 1 finish 
    move $a2 $a0 
    add $a0 $a0 $a1 
    move $a1 $a2 
    subu $a3 $a3 1
    j fib 
finish: 
    li $v0 1
    syscall 
    jr $ra 
baseCase: 
    move $a0 $a1 
    li $v0 1
    syscall
    li $v0 10 
    syscall 