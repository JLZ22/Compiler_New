# Asks the user to input a non negative number and outputs its 
# factorial. 
#
# @author John Zeng
# @date 12/08/2021
    .data 
        msgFirst: .asciiz "Please input a non-negative number: "

    .text 0x00400000
    .globl main
main: 
    li $v0 4
    la $a0 msgFirst 
    syscall 
    li $v0 5
    syscall 
    move $a0, $v0
    move $a1 $a0  

    beq $a0 0 zeroCondition 

    subu $sp $sp 4 
    sw $ra ($sp)
    jal fact
    lw $ra ($sp)
    addu $sp $sp 4
    li $v0 10
    syscall 

fact: 
    subu $a1 $a1 1
    beq $a1 0 finish 
    mult $a0 $a1 
    mflo $a0
    j fact
finish: 
    li $v0 1 
    syscall
    jr $ra 
zeroCondition: 
    li $v0 1
    li $a0 1
    syscall 
    li $v0 10
    syscall 