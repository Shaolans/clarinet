(function($, window, undefined){
    var Chatbox = function (options){
        this.init(options);
    };

    // creer la chatbox
    Chatbox.prototype = {
        constructor: Chatbox,
        init: function (options){
            var self = this;
            var opts = this.opts = setOption(options);
            var websocket = this.websocket = globalOptions.websocket;
            var boxFrame = '\
                <div class="chatbox" id="'+opts.boxId+'">\n\
                    <div class="chatbox-header">\n\
                        <div class="chatbox-title">'+opts.title+'</div>\n\
                        <div class="chatbox-options">\n\
                            <a class="minimize" href="#">-</a>\n\
                            <a class="close" href="#">X</a>\n\
                        </div>\n\
                        <br clear="all">\n\
                    </div>\n\
                    <div class="chatbox-body">\n\
                        <div class="chatbox-content"></div>\n\
                        <div class="chatbox-input">\n\
                            <textarea class="chatbox-textarea"></textarea>\n\
                        </div>\n\
                    </div>\n\
                </div>\n';
            $('.chatbox-container').append(boxFrame);

            var $elem = this.$elem = $('#'+opts.boxId);

            $elem.on('click',function(){
                $elem.addClass('chatbox-selected');
                $elem.find('.chatbox-textarea').focus();
            });
            
            $elem.find('.chatbox-textarea').on('focusout',function(){
                $elem.removeClass('chatbox-selected');
            });

            $elem.find('.minimize').on('click',function(event){
                event.preventDefault();
                $elem.find('.chatbox-body').slideToggle();
                return false;
            });
            
            $elem.find('.chatbox-title').on('click',function(){
                $elem.find('.chatbox-body').slideToggle(500);
            });

            $elem.find('.close').on('click',function(event){
                event.preventDefault();
                self.destroy();
                return false;
            });

            $elem.find('.chatbox-textarea').on('blur',function(){
                $(this).removeClass('chatbox-textarea-selected');
            }).on('focus',function(){
                $(this).addClass('chatbox-textarea-selected');
            });

            $elem.find('.chatbox-textarea').on('keydown',function(event){
                if(event.keyCode == 13){
                    event.preventDefault();
                    var date = new Date();
                    var current_time = '';
                    
                    if(date.getHours()<10){
                    	current_time += '0'
                    }
                    current_time += date.getHours() +':';
                    if(date.getMinutes()<10){
                    	current_time += '0'
                    }
                    current_time += date.getMinutes() +':';
                    if(date.getSeconds()<10){
                    	current_time += '0'
                    }
                    current_time += date.getSeconds();
                    var message = {
                    	type : opts.type,
                		from : globalOptions.name,
                		from_id : globalOptions.id,
                		to : opts.name,
                		to_id : opts.id,
    					content : $(this).val(),
    					time : current_time
        			};
                    self.message(JSON.stringify(message),'to');
                    return false;
                }
            });

            $elem.slideDown(500);

            $elem.data('chatbox',this);

            setCallback.call(this,'onChatboxCreate');

            debug('Chatbox create','[',this,$elem,']');
        },
        show: function(){
            this.$elem.find('.chatbox-body').slideDown(500);
        },
        hide: function(){
            this.$elem.find('.chatbox-body').slideUp(500);
        },
        messageTo: function(msg,timestamp){
            if (msg == ''){
                this.message('Can not send empty message','system');
            }else{
                msg = msg.replace(/^\s+|\s+$/g,""); // supprime les espaces
                msg = msg.replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/\"/g,"&quot;"); //supprime les pb d'affichage
                var msgItem = '\
                    <div class="chatbox-message">\n\
                        <span class="message-by">'+globalOptions.name+'('+timestamp+')'+'</span>\n\
                        <span class="message-content">'+msg+'</span>\n\
                    </div>\n';
                this.$elem.find('.chatbox-content').append(msgItem);
                this.$elem.find('.chatbox-content').scrollTop(this.$elem.find('.chatbox-content').get(0).scrollHeight);
                this.$elem.find('.chatbox-textarea').val('').focus();
                
                setCallback.call(this,'onMessageSend',msg);

                debug('Message send',this.opts.id,':',msg);
            }
        },
        message: function(jsonmsg,type){
        	var json = JSON.parse(jsonmsg);
        	var from = json.from;
        	var msg = json.content;
        	var timestamp = json.time;

        	var self = this;
            switch (type){
                case 'to':
                    this.messageTo(msg,timestamp);
                    var message = {
                    	type : this.opts.type,
                		from : globalOptions.name,
                		from_id : globalOptions.id,
                		to : this.opts.name,
                		to_id : this.opts.id,
    					content : msg,
    					time : timestamp
        			};
                    this.websocket.send(JSON.stringify(message));
                    break;
                case 'from':
                	
                    var msgItem = '\
                        <div class="chatbox-message">\n\
                            <span class="message-from">'+timestamp+'12'+from+'</span>\n\
                            <span class="message-content">'+msg+'</span>\n\
                        </div>\n';
                    this.$elem.find('.chatbox-content').append(msgItem);
                    this.$elem.find('.chatbox-content').scrollTop(this.$elem.find('.chatbox-content').get(0).scrollHeight);
                    this.blink();
                    this.animate();

                    setCallback.call(this,'onMessageReceive',msg);

                    debug('Message receive',this.opts.id,':',msg);
                    break;
                case 'system':
                    var msgItem = '\
                        <div class="chatbox-message">\n\
                            <span class="chatbox-info">'+msg+'</span>\n\
                        </div>\n';
                    this.$elem.find('.chatbox-content').append(msgItem);
                    this.$elem.find('.chatbox-content').scrollTop(this.$elem.find('.chatbox-content').get(0).scrollHeight);
                    this.blink();
                    this.animate();

                    setCallback.call(this,'onMessageSystem',msg);

                    debug('System message',this.opts.id,':',msg);
                    break;
            }
        },
        blink: function(){
            var self = this;
            var blinkTimes = 0;
            do{
                setTimeout(function(){
                    self.$elem.find('.chatbox-header').toggleClass('chatbox-blink');
                },300*blinkTimes);
                blinkTimes++;
                if (blinkTimes == 6){
                    blinkTimes = 0;
                    break;
                }
            }while(blinkTimes != 0);
        },
        animate: function(){
            var self = this;
            this.$elem
                .addClass('animated '+globalOptions.animate)
                .one('webkitAnimationEnd mozAnimationEnd oAnimationEnd animationEnd',function(){
                    $(this).removeClass('animated '+globalOptions.animate);
                });

            // quand le navigateur ne supporte pas
            setTimeout(function(){
                self.$elem.removeClass('animated '+globalOptions.animate);
            },800);

            return this;
        },
        destroy: function(){
            var self = this;
            this.$elem.fadeOut(500,function(){
                $(this).remove();
                boxInstance[self.opts.id] = null;
                delete boxInstance[self.opts.id];

                setCallback.call(self,'onChatboxDestroy');

                debug('Chatbox close','[',self,self.$elem,']');

                layout();
            });
        }
    };

    var boxInstance = {};

    function layout(){
        var align = 0;
        $.each(boxInstance, function(i){
            var ibox = $("#chatbox_"+i);
            var offset = align * (ibox.width()+5) + 20;

            debug('Chatbox realignment',ibox,' offset:',offset);
            ibox.css('right', offset+'px');
            align++;
        });
    }
    
    function setOption(options){
        options.boxId = globalOptions.idPrefix + options.id;
        options.enabled = true;
        if (options.title == null) {
            options.title = 'Chat: '+options.name;
        }
        return options;
    }
        
    // rangement des boites de dialogues
    function setCallback(callback){
        if (typeof this.opts[callback] === 'function'){
            this.opts[callback].apply(this, Array.prototype.slice.call(arguments, 1));
            debug(callback,this.opts[callback]);
        }else if (typeof globalOptions[callback] === 'function'){
            globalOptions[callback].apply(this, Array.prototype.slice.call(arguments, 1));
            debug(callback,globalOptions[callback]);
        }else{
            debug(callback,'No callback function set');
            return false;
        }
    }

    function debug(){
        if (globalOptions.debug == true){
            var logger = window.console['debug'];
            if (typeof logger === 'function'){
                logger.apply(window.console, arguments);
            }
        }
    }

    var globalOptions = {};

    var globalOptionsDefault = {
        id:null,
        name:null,
        debug:false,
        websocket:null,
        idPrefix:'chatbox_',
        animate:'bounce'
    }

    $.chatbox = function(opts){
        if (!$('.chatbox-container').length){
            $('body').append('<div class="chatbox-container"></div>');
        }
        if (typeof opts === 'object' && !boxInstance[opts.id]){
            globalOptions = $.extend({}, globalOptionsDefault, $.chatbox.globalOptions || {});

            var defaults = {
                id:null,
                name:null,
                title:null,
                type:null
            };

            var options = $.extend(defaults, opts || {});

            boxInstance[options.id] = new Chatbox(options);

            debug('Chatbox instance collections',boxInstance);
            layout();
        }
        else if(typeof opts === 'number' || typeof opts === 'string'){
            if (boxInstance[opts] != undefined){
                return boxInstance[opts];
            }else{
                debug('Error','Chatbox not exist')
            }
        }else{
            return false;
        }
    };

    $.chatbox.getQueue = function(){
        return boxInstance;
    }

    $.chatbox.globalOptions = globalOptionsDefault;

})(jQuery, window);