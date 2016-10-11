#import "DataDump.h"

@implementation DataDump

-(NSMutableArray *)volcado:(CDVInvokedUrlCommand*) command {
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    //NSLog(@"+++ %@ +++", [defaults arrayForKey: @"userlist"]);
    
    if ([defaults arrayForKey:@"userlist"] != nil) {
        /*NSData *jsonData = [NSJSONSerialization dataWithJSONObject:[defaults arrayForKey:@"userlist"] options:NSJSONWritingPrettyPrinted error:nil];
        NSString *jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
        NSLog(@"%@", jsonString);*/
        
        NSMutableArray *array = [[NSMutableArray alloc] init];
        [array addObjectsFromArray:[defaults arrayForKey:@"userlist"]];
        NSLog(@"%@", array);
        
        
        return array;
    } else {
        return nil;
    }
}

@end