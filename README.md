
# description
> This is a utility for monitoring in progressing the project.
Although there are already several existing monitoring tools, there is a multiple monitoring tools, but if the project is on, the scale is large (if you are scaled out or branching the server according to each function(service(?)))
You will need to install the agent to monitor for each branched server.
Multi-monitoring utility enables monitoring without having to install a separate agent on the monitoring server by adding only instance information of the server to the configuration file every time the server branches.
Monitoring means that when a specific keyword is specified and the keyword is printed in the log, You can send the information by e-mail to receive it.


# prerequisite 
> If the monitored server and monitoring server are different instance,
> You must allow port 22 on monitored server from the monitoring server.

# GETTING STARTED
### Unzip the archive file(tar)

### You need to write a configuration file.
```shell
$vi ${projectDir}/conf/application.yml
```
### detail configuration
```yaml
  # List keywords to be detected in array. 
  notiKeywords:
    - ERROR

  # write the SMTP information
  mail:
    host: stmp.gmail.com 
    port: 587
    account: account
    password: password 
    from: sender@gmail.com

    # write a receiving list in array form. 
    to:
      - receiver@gmail.com


  # write servers to monitor in array form.
  servers:
    ## instance name (alias)
    - name: prod-collector 
      host: hostAddr
      remoteAccnt: ec2-user 
      logPath: /collector/logs/collector.log
      port: 22

      # You have to decide on one(😱only one😱) authentication method. (password or authentication key)
      # if you want to use password you have to uncomment 
      # password: blabla 
      authKeyPath: /home/ec2-user/blabla.pem
```
### execute startup.sh 
> If you have completed all the settings above, you can execute monitoring process
```shell
$ sh ${projectDir}/bin/startup.sh
```