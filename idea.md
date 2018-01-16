##idea修改远程仓库地址
 网址：https://www.cnblogs.com/liaojie970/p/8027656.html
 * IDEA git修改远程仓库地址[3种方法]
   方法1.修改命令
    git remote set-url origin <url>
   方法2.先删后加
    git remote rm origin
    git remote add origin [url]
   方法3.直接修改config文件
    找到項目文件【本地idea的工作区间中】--.git文件夹下的config文件【修改remote 的URL值】即可。
    [core]
    	repositoryformatversion = 0
    	filemode = false
    	bare = false
    	logallrefupdates = true
    	symlinks = false
    	ignorecase = true
    [remote "origin"]
    	url = https://gitee.com/GuTaiRuanJian/ycyy.git
    	fetch = +refs/heads/*:refs/remotes/origin/*
    [branch "ycyy"]
    	remote = origin
    	merge = refs/heads/ycyy
 
 * 
   
